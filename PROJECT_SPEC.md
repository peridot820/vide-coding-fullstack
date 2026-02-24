# PROJECT_SPEC.md

## 프로젝트 설정
- JDK: 25 이상
- Language: Java
- Spring Boot: 4.0.1 이상
- Build Tool: Gradle 9.3.0 이상, Groovy DSL 사용
- Dependencies: Thymeleaf 추가됨

## Project Metadata
- Group: com.example
- Artifact: vibeapp
- Main Class Name: VibeApp
- Description: 최소 기능 스프링부트 애플리케이션을 생성하는 프로젝트다.
- Configuration: YAML 파일 사용

## Project Structure (Feature-based)
- `com.example.vibeapp.home`: HomeController, home templates
- `com.example.vibeapp.post`: Post, PostController, PostService, PostRepository, post templates

## 프로젝트 상태
- [x] 프로젝트 초기화
- [x] "Hello, Vibe!" REST API 구현
- [x] Thymeleaf 뷰 템플릿 엔진 추가
- [x] index.html 기반 웹 페이지 구현
- [x] 기능 기반 패키지 구조 리팩토링 (home, post)
- [x] 게시글 페이징 기능 구현
- [x] 뷰 템플릿 파일 기능별 위치 변경
- [x] 자바 클래스 메서드명 실무 관례 적용 및 최적화
- [x] 프로젝트 명세서(PROJECT_SPEC.md) 최신화
- [ ] Git 커밋 및 푸시 (진행 예정)
