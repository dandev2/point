# point
 h2 database와 springboot를 사용하여 포인트 api를 개발하였습니다.

# 리눅스 터미널에서 Springboot 빌드하고 실행시키는법
1. C(혹은 D) 드라이브로 이동하려면 '/mnt' 쪽으로 이동
2. 프로젝트의 경로로 이동
3. 프로젝트에 적용할 자바 설치하기 (명령어 : sudo apt install openjdk-8-jdk-headless)
4. 자바가 설치되면 springboot 빌드 (명령어 : ./gradlew build)
5. 빌드가 성공적으로 됐으면 build 폴더로 이동 후 생성된 자바 실행 (명령어 : cd build/libs)
6. 이동 후 ls 명령어를 통해 자바 파일 생성된 것을 확인한 후
7. 다음의 명령어 입력 (명령어 : java -jar webservice-0.0.2.jar)
8. 스프링부트 프로젝트 실행됨.

참고 사이트 : https://koogood.tistory.com/4
