# AquaVita

## Sobre o Projeto

AquaVita é um aplicativo móvel dedicado à preservação e gestão consciente da água. Com o lema "Preservando o futuro, gota a gota", o aplicativo oferece informações sobre pontos de distribuição de água, notícias relevantes sobre o tema, dicas de preservação e conteúdo educativo.

## Funcionalidades

- **Sistema de Autenticação**: Login e cadastro de usuários
- **Feed de Notícias**: Artigos atualizados sobre água e sustentabilidade
- **Mapa Interativo**: Visualização de pontos de distribuição de água em diferentes cidades
- **Dicas de Preservação**: Conteúdo educativo sobre uso consciente da água
- **Quiz Educativo**: Teste de conhecimentos sobre água e preservação ambiental

## Tecnologias Utilizadas

- **Linguagem**: Kotlin
- **Framework UI**: Jetpack Compose
- **Arquitetura**: MVVM (Model-View-ViewModel)
- **Navegação**: Jetpack Navigation Component
- **Mapas**: MapLibre
- **Rede**: Retrofit (para consumo de APIs)

## Requisitos

- Android Studio Arctic Fox ou superior
- JDK 11 ou superior
- Dispositivo ou emulador com Android API 21 (Lollipop) ou superior

## Instalação

1. Clone o repositório:

`git clone https://github.com/seu-usuario/aquavita.git`

2. Abra o projeto no Android Studio

3. Sincronize o projeto com os arquivos Gradle

4. Execute o aplicativo em um emulador ou dispositivo físico

## Estrutura do Projeto

- **app/src/main/java/com/fiap/aquavita/**
    - **composable/**: Componentes Composable reutilizáveis
        - **screens/**: Telas do aplicativo
    - **models/**: Classes de modelos
    - **nav/**: Classe de Navegação
    - **services/**: Lógica de busca da API
    - **ui/theme/**: Definições de tema e estilos
    - **utils/**: Classes Auxiliares
    - **viewmodels/**: ViewModels para gerenciamento de estado

## Screenshots

- Home
  ![Tela inicial do AquaVita](https://prnt.sc/gwvOYpnydU70)

- Dicas
  ![Dicas](https://prnt.sc/ncDHODgV8sN6)

- Quiz
  ![Quiz](https://prnt.sc/8ZwcSDRjbbOc)

- Mapa com zonas de ajuda
  ![Mapa com zonas de ajuda](https://prnt.sc/P0Gib98f7n0Z)

- Modal ao clicar na zona de ajuda
  ![Modal ao clicar na zona de ajuda](https://prnt.sc/prbWU9qMIJ01)

---

*Desenvolvido como projeto da Global Solutions FIAP*