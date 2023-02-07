# tims_crawling
## 🚨 Spring version 이슈 정리
### BackEnd
1. 로그인 정보 불일치 시 Whitelabel Error Page (내부: NPE - tr 태그 못찾음)
   1. 사용자에게 로그인 다시 하도록 유도
2. Tims에 로그인 되어있는 경우 로그인이 되지 않는 에러
   1. Tims로 로그인 다시 시도하여 정상 접속 되도록 고치기
### FrontEnd
1. 로그인 정보 불일치 시 Whitelabel Error Page (내부: NPE - tr 태그 못찾음)
   1. alert을 통해 사용자에게 로그인 다시 하도록 유도

## Spring version 1차 개발 (완료)
### BackEnd
1. 일주일 근무시간 계산 기능
2. 전문연구요원 지각 시간 계산 기능
### FrontEnd
1. 일주일 근무시간 계산하여 progress bar에 표시
2. 전문연구요원 지각 시간 표시

## Spring version 2차 개발 범위 (진행 중)
### BackEnd
1. 일반 연구원 / 전문연구요원 구분 값 dto에 포함하여 response
2. 금요일 퇴근 가능 시각 표시 기능
3. 팀장 근무시간 계산기능 추가
4. 최소한의 리팩터링 필요
5. 모든 요청에 대한 로그 추가 (yunhye-choi)

### FrontEnd
1. 디자인 입히기
2. 전문연구요원인 경우 지각 시간 조회 가능하도록 변경
3. 일반 연구원인 경우 금요일 퇴근 가능 시각 표시 기능

### Devops
1. AWS로 배포

## Spring version 3차 개발 범위 (기획 중)
### BackEnd
1. 월~금 출퇴근 시간 조회 기능
2. 지각 시간, 근무 시간 ranking 기능 (백분위)
### FrontEnd
1. 월~금 출퇴근 시간 조회 표시 기능
2. 근무시간 100퍼센트 달성 시 만세 + 따봉
3. 지각 시간, 근무 시간 ranking 기능 (백분위)
