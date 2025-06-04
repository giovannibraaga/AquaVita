# AquaVita

<img src="https://github.com/user-attachments/assets/3759cb42-8642-4104-b95e-9cd68a0076be" alt="AquaVita-logo" width="250" height="250"/>

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

### Home
  ![Home Screen](https://github.com/user-attachments/assets/9c829382-4931-4ba3-bc31-e030d7fc51c5)


### Dicas
  ![Dicas](https://github.com/user-attachments/assets/591d0105-309b-4a4c-aad8-64efeae3f9ee)


### Quiz
![Quiz](https://github.com/user-attachments/assets/91ff381d-bbb5-45cf-a148-635b1bd69fa0)


### Mapa com zonas de ajuda
  ![Mapa com zonas de ajuda](https://github.com/user-attachments/assets/5cf4c701-6459-45f5-a6dc-9d7087717e28)


### Modal ao clicar na zona de ajuda
  ![Modal ao clicar na zona de ajuda](https://github.com/user-attachments/assets/4b7c9603-2599-4089-8b97-4513992026ac)


---

*Desenvolvido como projeto da Global Solutions FIAP*
