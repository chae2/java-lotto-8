# java-lotto-precourse
### 🚀 프로젝트 소개
- 사용자가 로또 구입 금액과 당첨 번호, 보너스 번호를 입력 시 로또 N개의 당첨 여부를 계산해주는 프로그램입니다!
### 🚀 주요 기능
1. 로또 구입 금액을 입력받는다. (1,000원 단위)
   - 구입 금액이 양수가 아닌 경우 `IllegalArgumentException`
   - 1000으로 나누어 떨어지지 않을 경우 `IllegalArgumentException`
2. 구입 금액에 해당하는 만큼 로또를 발행하고, 발행된 로또 개수를 출력한다.
3. 입력된 당첨 번호 `winning_numbers` 형식 검증
    - `,`를 통해 문자열이 6개 번호로 나누어지는지
    - 번호들이 양수가 아닐 경우 `IllegalArgumentException`
    - 번호들이 1과 45 사이에 있지 않는 경우 `IllegalArgumentException`
4. 입력된 보너스 번호 `bonus_number` 형식 검증
    - 번호가 양수가 아닐 경우 `IllegalArgumentException`
    - 번호가 1과 45 사이에 있지 않는 경우 `IllegalArgumentException`
5. 발행된 모든 로또 번호와 당첨 번호를 비교하여 당첨 내역을 계산한다.
6. 최종 당첨 통계와 총 수익률을 계산하여 출력한다. (소수점 둘째 자리에서 반올림)