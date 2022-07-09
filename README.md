## 🔍Search GitHub Repository
GitHub API를 사용하여 GitHub Repository 조회하고 리스트를 보여주는 앱
## 개발 기간
- 2022.07.06 ~ 2022.07.10
## 기술스택 & 라이브러리
- IDE -> Android Studio
- Language -> Kotlin
- Network -> Retrofit, RxJava
- Dependency injection -> Koin
- MVVM Architecture
- Android Jetpack(ViewModel, LiveData, RecyclerView)
- ViewBinding
- Glide
## 사용 API
-  GitHub API
https://docs.github.com/en/rest/search#search-repositories

## 기능 
- gitHub api를 이용하여 github repoistory 검색 (Retorift, Rxjava 사용)
- Recyclerview의 최하단 감지하여 페이징 처리
- glide를 이용하여 이미지 보여줌 ( thumbnail(0.1f) -> 이미지 미리보여주기)

## 코드 구조 설명
- [di]
  - MyKoin : Koin 모듈 생성
- [model]
  - resonse  : 응답받는 데이터
  - service
    - GitHubService : API Interface 
  - DataModel : viewModel에서 dataModel 실행하면 결과값 single로 받음
  - DataModelImpl : datamodel 구현체
- [ui]
  - MainActivity : 
    - 검색버튼 클릭 시 viewModel getGitHub 함수에 값 전달
    - recyclerview 최하단 감지 시 page값 증가시켜 viewModel getGitHub 함수에 전달
    - api 결과 리스트를 받아 recylcerviewAdapter에 연결
    - api 결과 리스트가 비어 있거나 error 시 Toast창 띄움
  - MainAdapter : setList 함수에서 repositoryList에 값을 추가
  - MainViewModel : datamodel을 실행하며 결과값을 받아 Livedata를 이용하여 Activity에 전달


## 미리보기
![KakaoTalk_20220710_013521461_01](https://user-images.githubusercontent.com/38210019/178116527-92e9ebda-4b2a-4701-937e-9e0444213ad7.gif)
![KakaoTalk_20220710_013521461](https://user-images.githubusercontent.com/38210019/178116525-77a3c265-849e-4d49-b8cd-9528a9a040d4.gif)
