# Gerenciador de Árvore de Dependências de Projeto

Uma aplicação desktop desenvolvida para ajudar equipes de engenharia de software a visualizar, gerenciar e ordenar a execução de tarefas complexas que possuem dependências entre si. O sistema modela os pré-requisitos utilizando uma estrutura de **Árvore Binária** e calcula a fila de execução exata sem violar nenhuma dependência prévia.

---

## 🚀 Funcionalidades

O projeto conta com uma arquitetura totalmente desacoplada e componentizada dividida em módulos visuais:

* **Cadastro Geral de Tarefas:** Registro unificado de tarefas em memória (`TarefaServico`), prevenindo a duplicidade de IDs através de validações automáticas.
* **Vínculo Dinâmico de Nós:** Interface dedicada para conectar tarefas como dependências de um nó pai específico, utilizando regras rígidas de posicionamento (`ESQUERDA` e `DIREITA`).
* **Visualização Gráfica Interativa (`JTree`):** Renderização visual e em tempo real da hierarquia de módulos da árvore. Os nós podem ser expandidos ou recolhidos pelo usuário.
* **Cálculo da Fila de Execução (Pós-Ordem):** Algoritmo que dita a ordem cronológica exata que a equipe deve seguir. Como utiliza o percurso **Pós-Ordem**, ele garante matematicamente que todas as sub-tarefas (filhos) sejam concluídas *antes* da tarefa principal (pai).

---

## 🛠️ Tecnologias Utilizadas

A pilha tecnológica escolhida prioriza robustez, performance nativa e uma arquitetura limpa:

| Tecnologia | Utilização |
| :--- | :--- |
| **Java 21 (LTS)** | Linguagem base do sistema, utilizando recursos modernos de POO. |
| **Java Swing** | Biblioteca nativa para a construção da interface gráfica (GUI). |
| **Padrão MVC / Mediador** | Arquitetura desacoplada onde os painéis visuais se comunicam de forma coordenada. |

---

## 📦 Como Baixar e Rodar o Projeto

### Pré-requisitos
Certifique-se de ter instalado em sua máquina:
* **Java Development Kit (JDK) 21** ou superior.
* Uma IDE de sua preferência (Eclipse, IntelliJ IDEA, NetBeans) ou o terminal de comando.

### Passo a Passo

1.  **Clonar o repositório:**
    ```bash
    git clone [https://github.com/Letterson-Enterprise/arvoreDependenciaDeProjetos.git](https://github.com/Letterson-Enterprise/arvoreDependenciaDeProjetos.git)
    ```
2.  **Navegar até a pasta do projeto:**
    ```bash
    cd <repositorio>
    ```
3.  **Compilar o projeto via Terminal:**
    ```bash
    javac -d bin src/br/com/lettersonEnterprise/**/*.java
    ```
4.  **Executar a aplicação:**
    ```bash
    java -cp bin br.com.lettersonEnterprise.visao.JanelaPrincipal
    ```

> **Dica:** Se estiver utilizando uma IDE (como Eclipse ou IntelliJ), basta importar a pasta raiz do projeto como um projeto Java existente e executar o método `main` localizado na classe `JanelaPrincipal.java`.

---

## 🔮 Futuras Implementações

O projeto foi estruturado pensando em escalabilidade. As próximas evoluções planejadas para o sistema são:

* **UI e UX Melhorado:** Implementação de bibliotecas visuais modernas (como o Look and Feel *FlatLaf*) para aplicar um design limpo, responsivo e com suporte a *Dark Mode* (Tema Escuro).
* **Múltiplas Árvores de Dependência:** Evoluir o sistema para que o usuário não fique limitado a apenas uma raiz padrão (ID 1). Será adicionada a capacidade de criar, salvar e alternar entre diferentes árvores de projetos simultaneamente na mesma sessão.