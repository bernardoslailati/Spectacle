# Spectacle

<div>
  <img alt="Android" src="https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" />
  <img alt="Kotlin" src="https://img.shields.io/badge/kotlin-%230095D5.svg?style=for-the-badge&logo=kotlin&logoColor=white" />
</div>

### *Busque e favorite músicas e filmes do momento.*

## Funcionalidades
- Registro, login e autenticação de usuário
- Lista de músicas favoritas
- Lista de filmes favoritos
- Lista de novas músicas a serem adicionadas
- Lista de novos filmes a serem adicionados (por gênero)

## Telas

__-- Login__

__-------- Cadastro de Usuário__

__-- Menu (músicas ou filmes)__

__-------- Minha Playlist de Músicas__

__-------------- Novas Músicas__

__-------- Meus Filmes (por gênero)__

__-------------- Novos Filmes (por gênero)__

## Principais Bibliotecas
- Koin
- Firebase
- Coroutines
- Navigation Components
- ROOM
- Retrofit2
- Glide
- Lottie
- DataStore
- LiveData
- Flow
- OkHttp3
- Gson

## Funcionalidades
- Adicionar (acessando tela de novas músicas) e remover (arrastar para o lado esquerdo item de música em lista) músicas favoritas
- Adicionar (acessando tela de novos filmes) e remover (ação de segurar item de filme em lista - _onLongClick()_) filmes favoritos
- Logar e se manter logado em futuras sessões
- Logout
- Registrar usuário (utilizando o _Firebase Authentication_)

## APIs
- [Deezer](https://rapidapi.com/deezerdevs/api/deezer-1) (novas músicas)
- [The Movie Database](https://developers.themoviedb.org/3/getting-started/introduction) (novos filmes por gênero)

 ![ic_app_launcher_round](https://user-images.githubusercontent.com/44252209/175922126-c8dab67a-c3fc-4bdb-9437-a350f7ce0302.png)
 
 
 # Programação Orientada a Objetos (Kotlin)  

## Planejamento  

<table>  
<tr>  
<th>Objetivos</th>  
<th>Conteúdos</th>  
<th>Bibliografia</th>  
<th>Metodologia</th>  
<th>Avaliação</th>  
<th>Observações</th>  
</tr>  
<tr>  
<td>
<ul> 
<li>Aprender como criar telas complexas, englobando diferentes tipos de elementos visuais e como personalizar seus comportamentos de maneira efetiva;</li>
<li>Consumir e gerenciar dados de fontes internas (banco de dados) e externas (APIs), seguindo padrões recomendados e utilizando bibliotecas aplicadas em grandes projetos;</li>
<li>Gerenciar a escrita e leitura de arquivos de mídia e documentos, internamente no dispositivo;</li>  
<li>Conhecer o pacote de bibliotecas Jetpack, o por quê foi criado e alguns dos cenários mais aplicáveis em projetos Android;</li>   
<li>Conhecer alguns dos principais serviços disponibilizados pela Google para aplicativos Android, como configurá-los e utilizá-los em projetos.</li>  
</ul>  
</td>  
<td>  
<ul>
<li>Elementos visuais complementares para a captura de eventos de escolha dos usuários (Switch, Slider, CheckBox, Toggle Buttons, RadioButton/RadioGroup, Chip/ChipGroup)</li>  
<li>Reutilização de layouts utilizando CustomViews e includes</li> 
<li>Caixas de diálogo e conteúdo sobrepostas (DialogFragment e BottomSheetFragment)</li>  
<li>Gerenciamento de banco de dados local SQLite utilizando ROOM</li>  
<li>Consumo de APIs utilizando Retrofit2 e OKHttp</li>  
<li>Escrita e leitura de dados de chave-valor em arquivo XML via SharedPreference</li>  
<li>Escrita e leitura de dados em arquivos internos específicos do dispositivo</li>  
<li>Visão geral de algumas das principais bibliotecas do pacote Jetpack</li>  
<li>Navegação entre telas utilizando a biblioteca Navigation Components</li>  
<li>Observação de mudança de estado de variáveis com LiveData</li> 
<li>Visão geral de serviços Google disponíveis para Android</li>
<li>Contextualização dos serviços entregues pelo Firebase</li>
<li>Integração de aplicativo com Firebase e consumo de serviços</li>
<li>Utilização de APIs de mapas via Google Maps API</li>
</ul>  
</td>  
<td>  
Links:  
<ul>  
<li>https://developer.android.com/guide/topics/ui/controls/radiobutton?hl=pt-br</li>
<li>https://developer.android.com/reference/android/widget/RadioGroup?hl=pt-br</li>
<li>https://developer.android.com/reference/com/google/android/material/chip/Chip</li>
<li>https://developer.android.com/reference/com/google/android/material/chip/ChipGroup</li>
<li>https://developer.android.com/reference/com/google/android/material/slider/Slider</li>
<li>https://developer.android.com/reference/android/widget/Switch</li>
<li>https://developer.android.com/develop/ui/views/components/togglebutton</li>
<li>https://developer.android.com/reference/android/widget/CheckBox</li>
<li>https://developer.android.com/develop/ui/views/components/dialogs</li>
<li>https://developer.android.com/reference/com/google/android/material/bottomsheet/BottomSheetDialog</li>
<li>https://developer.android.com/training/data-storage/room</li>
<li>https://square.github.io/retrofit/</li>
<li>https://square.github.io/okhttp/</li>
<li>https://developer.android.com/training/data-storage</li>
<li>https://developer.android.com/training/data-storage/shared-preferences</li>
<li>https://developer.android.com/training/data-storage/app-specific</li>
<li>https://developer.android.com/jetpack?gclid=CjwKCAjwpqCZBhAbEiwAa7pXecR503f-4Y389hTTAryivFwv6Hu4KkZicorPGpboE5xFVPHRgtGtnhoCo3MQAvD_BwE&gclsrc=aw.ds</li>
<li>https://developer.android.com/jetpack/getting-started</li>
<li>https://developer.android.com/jetpack/androidx/explorer</li>
<li>https://developer.android.com/guide/navigation?gclid=CjwKCAjwpqCZBhAbEiwAa7pXeb_0cdoEUBp0guEl29gOKMu9-9nsxoB1PL57RSWE0ppc79LcLsyhuxoCtFIQAvD_BwE&gclsrc=aw.ds</li>
<li>https://developer.android.com/topic/libraries/architecture/livedata?hl=pt-br</li>
<li>https://firebase.google.com/</li>
<li>https://firebase.google.com/docs/android/setup?hl=pt-br</li>
<li>https://developers.google.com/maps/documentation/android-sdk/overview</li>
<li>https://developers.google.com/maps/documentation/android-sdk/start</li>
</ul>  
Livros:  
<ul>  
<li>Use a cabeça! Desenvolvendo para Android</li>
<li>Head First Android Development: A Learner's Guide to Building Android Apps with Kotlin</li>
<li>Kickstart Modern Android Development with Jetpack and Kotlin: Enhance your applications by integrating Jetpack and applying modern app architectural concepts</li>
</ul>  
</td>  
<td>  
<ul>  
<li>Estudos de casos e problemas reais;</li>
<li>Resolução de problemas em duplas (pair programming);</li>
<li>Projetos individuais;</li>
<li>Lista de exercícios;</li>
<li>Pesquisas individuais em documentações oficiais;</li>
<li>Gamificação de perguntas e respostas com Kahoot sobre o assunto da aula.</li>
</ul>  
</td>  
<td>  
<ul>  
<li>Lista de exercícios;</li>
<li>Execução de projetos contemplando os assuntos abordados em aula;</li>
<li>Elaboração de documentos descritivos de execução de projeto.</li>
</ul>  
</td>  
<td>  
Os elementos visuais adicionais a serem mostrados (CheckBox, RadioGroup, Slider, etc.) deverão ser introduzidos de forma direta e superficial (já utilizar um código pronto, de preferência), visando focar em mostrar como e quando utilizá-los, pois o foco do tema de views avançado deverá ser a reutilização de layouts e criação de  telas sobrepostas de conteúdo.
</td>  
</tr>  
</table>

