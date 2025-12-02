<#
run.ps1 — script para desenvolver/rodar o projeto Java localmente (Windows PowerShell)
Usos:
  - ./run.ps1            => limpa classes em src, compila todas as fontes para bin e executa Main
  - ./run.ps1 -CleanOnly => apenas limpa .class dentro de src e bin
  - ./run.ps1 -NoRun    => compila mas não executa a aplicação
#>

[CmdletBinding()]
param(
    [switch]$CleanOnly,
    [switch]$NoRun
)

$ErrorActionPreference = 'Stop'

function Write-Info($msg) { Write-Host "[INFO] $msg" -ForegroundColor Cyan }
function Write-Err($msg)  { Write-Host "[ERROR] $msg" -ForegroundColor Red }

$scriptDir = Split-Path -Parent $MyInvocation.MyCommand.Definition
Set-Location $scriptDir

$srcDir = Join-Path $scriptDir 'src'
$binDir = Join-Path $scriptDir 'bin'

Write-Info "Projeto: $scriptDir"
Write-Info "Fonte: $srcDir"
Write-Info "Saida (bin): $binDir"

if (-not (Test-Path $srcDir)) {
    Write-Err "Diretorio 'src' nao encontrado. Execute este script na raiz do projeto."; exit 1
}

if ($CleanOnly) {
    Write-Info "Executando limpeza de .class em 'src' e 'bin'..."
    Get-ChildItem -Path $srcDir -Recurse -Filter '*.class' -ErrorAction SilentlyContinue | Remove-Item -Force -ErrorAction SilentlyContinue
    if (Test-Path $binDir) { Get-ChildItem -Path $binDir -Recurse -Filter '*.class' -ErrorAction SilentlyContinue | Remove-Item -Force -ErrorAction SilentlyContinue }
    Write-Info "Limpeza concluida."; exit 0
}

# Remove classes compiladas acidentalmente em src (evita carregar classes antigas se src estiver no classpath)
Write-Info "Removendo possiveis .class dentro de src (se existirem)..."
Get-ChildItem -Path $srcDir -Recurse -Filter '*.class' -ErrorAction SilentlyContinue | ForEach-Object {
    try { Remove-Item -Path $_.FullName -Force -ErrorAction Stop; Write-Info "Removido: $($_.FullName)" } catch { Write-Err "Falha ao remover $($_.FullName): $_" }
}

# Garante que bin existe
if (-not (Test-Path $binDir)) { New-Item -ItemType Directory -Path $binDir | Out-Null }

# Localiza todos os .java
Write-Info "Procurando arquivos .java em src..."
$javaFiles = Get-ChildItem -Path $srcDir -Recurse -Filter '*.java' | ForEach-Object { $_.FullName }
if (-not $javaFiles) { Write-Err "Nenhum arquivo .java encontrado em $srcDir"; exit 1 }

# Verifica se javac e java estão disponíveis
try {
    & javac -version > $null 2>&1
} catch {
    Write-Err "O compilador 'javac' nao foi encontrado no PATH. Verifique sua instalacao do JDK."; exit 1
}
try {
    & java -version > $null 2>&1
} catch {
    Write-Err "O runtime 'java' nao foi encontrado no PATH. Verifique sua instalacao do JRE/JDK."; exit 1
}

# Compilar
Write-Info "Compilando ${($javaFiles).Count} arquivos Java para '$binDir'..."
$javacArgs = @('-d', $binDir) + $javaFiles
$proc = Start-Process -FilePath javac -ArgumentList $javacArgs -NoNewWindow -Wait -PassThru -RedirectStandardError "javac_err.txt" -RedirectStandardOutput "javac_out.txt"
if ($proc.ExitCode -ne 0) {
    Write-Err "Compilacao falhou. Saida do javac:"; Get-Content javac_err.txt -Raw | Write-Host
    exit $proc.ExitCode
}
Write-Info "Compilacao concluida com sucesso."

if ($NoRun) { Write-Info "Opcao -NoRun fornecida - parado apos compilacao."; exit 0 }

# Executar
Write-Info "Iniciando aplicacao (Main) com classpath '$binDir'..."
try {
    # Executa no terminal atual para que logs apareçam para o desenvolvedor
    & java -cp $binDir Main
} catch {
    Write-Err "Erro ao executar a aplicacao: $_"; exit 1
}

Write-Info "Processo finalizado." 
