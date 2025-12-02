# Projeto Java - Cadastro de Alunos

Este projeto é uma aplicação de exemplo em Java (Swing) para cadastro de alunos. Inclui uma GUI simples, um controller, um DAO em memória e persistência em arquivo.

## Estrutura do projeto

- `src/` - código-fonte Java
  - `controller/` - controladores (ex.: `AlunoController.java`)
  - `model/` - modelos e DAO (`Aluno.java`, `AlunoDAO.java`)
  - `view/` - classes Swing (`AlunoGUI.java`, `LoginGUI.java`)
  - `Main.java` - ponto de entrada da aplicação
- `bin/` - classes compiladas (gerado por `javac` ou pelo script)
- `alunos.txt` - arquivo de persistência gerado pelo `AlunoDAO`
- `run.ps1` - script PowerShell para limpar, compilar e executar a aplicação


## Requisitos

- JDK 11+ (testado com JDK 21)
- Windows PowerShell (para usar o `run.ps1`) — ou você pode compilar manualmente com `javac` e executar com `java`.


## Como compilar e executar

Opção recomendada (script PowerShell criado no repositório):

1. Abra PowerShell na pasta do projeto (onde está o `run.ps1`).
2. Para compilar e executar:

```powershell
powershell -ExecutionPolicy Bypass -File .\\run.ps1
```

3. Para apenas compilar (sem executar a GUI):

```powershell
powershell -ExecutionPolicy Bypass -File .\\run.ps1 -NoRun
```

4. Para limpar `.class` que estejam dentro de `src` e `bin`:

```powershell
powershell -ExecutionPolicy Bypass -File .\\run.ps1 -CleanOnly
```


Compilação/execução manual (alternativa):

```powershell
# Compilar
javac -d bin src\\Main.java src\\controller\\*.java src\\model\\*.java src\\view\\*.java
# Executar
java -cp .\\bin Main
```


## Testes rápidos (fluxo de validação)

- Abra a aplicação e acesse a tela de cadastro.
- Testes de validação do campo `validarCampos` (implementado em `AlunoController`):
  - Nome válido: `João da Silva` (aceita acentos)
  - Nome com hífen/apóstrofo: `Ana-Clara`, `O'Neill`
  - Nome inválido: `João123` → deve mostrar erro
  - Matrícula válida: `12345`
  - Matrícula inválida: `12A45` → deve mostrar erro
  - Telefone formatado: `(11) 98765-4321` → deve ser aceito (o script de validação remove qualquer não-dígito antes de validar)
  - Telefone inválido (curto): `1234` → deve mostrar erro
  - Curso: deixe em `Selecione` → deve mostrar erro

Ao submeter dados válidos, a aplicação mostra o popup `DADOS CADASTRADOS COM SUCESSO!` e atualiza a tabela de alunos.


## Debugging e dicas práticas

- Sempre salve suas alterações antes de compilar (Ctrl+S).
- Garanta que você rode a JVM com `-cp bin` (ou configure sua IDE para usar a pasta `bin`/out como classpath de execução).
- Se alterações não aparecerem:
  - Limpe `bin` e quaisquer `.class` dentro de `src` e recompile.
  - Verifique cópias duplicadas:

```powershell
Get-ChildItem -Path . -Recurse -Filter AlunoController.class | Select-Object FullName, LastWriteTime
```

- Se usar IDE (Eclipse/IntelliJ), prefira a configuração de "Build Automatically" ou faça `Build -> Rebuild Project`.


## Estrutura de arquivos alterados nesta sessão

- `src/controller/AlunoController.java` — validação e tratamento de entradas (corrigido)
- `run.ps1` — script PowerShell para limpar, compilar e executar
- `README.md` — este arquivo (documentação)


## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
