# ticket-price-modeling

## お題
料金表から映画料金を計算するドメインモデルの作成

https://cinemacity.co.jp/ticket/
#チケット料金モデリング のタイムライン

## スコープ外
- 祝日
- エムアイカード、駐車場パーク80割引、夫婦50割引
- ※3D作品は一律プラス400円。3Dメガネ（Real D）持参の場合は、100円引き。
- ※【極上爆音上映】はレイトショー割適用外です

## 工夫点

### 購入条件を宣言的に定義
チケットの購入条件をそのままif文等で分岐させるとコードが複雑になるため、
購入条件をEnumに宣言的に記述して、ロジックの複雑さを閉じ込める工夫をしました。
TicketRuleでは対象となるチケットとその購入条件の組合せを宣言しています。
`matches(Customer customer)`で購入可能なチケットの候補を取得できます。

``` java
/* チケットの種類とその該当条件 */
public enum TicketRule {
    /* シネマシティズン */
    CINEMA_CITIZEN(TicketTable.CINEMA_CITIZEN, Customer::isCinemaCitizen),
    /* シネマシティズン(60歳以上) */
    ELDER_CINEMA_CITIZEN(TicketTable.ELDER_CINEMA_CITIZEN, c -> c.isCinemaCitizen() && c.isOver60()),
    /* 一般 */
    NORMAL(TicketTable.NORMAL, c -> true),
    // ...略
    private final TicketTable ticketTable;
    private final Predicate<Customer> condition;
    
    public static List<TicketTable> matches(Customer customer) {
        return Stream.of(TicketRule.values())
            .filter(t -> t.test(customer))
            .map(t -> t.ticketTable)
            .collect(Collectors.toList());
    }
}
```

チケット候補と同様に上映タームもEnumで同様に宣言しており、`matches(Today today)`から現在の上映タームを取得できます。

``` java
public enum TermRule {
    /* 映画の日 */
    MOVIE_DAY(Today::isMovieDay),
    /* 平日 (～20:00) */
    EARLY_WEEKDAY(today -> today.isEarlyTime() && today.isWeekDay()),
    /* 平日 (20:00～) */
    LATE_WEEKDAY(today -> today.isLateTime() && today.isWeekDay()),
    /* 土日 (～20:00) */
    EARLY_WEEKEND(today -> today.isEarlyTime() && today.isWeekEnd()),
    /* 土日 (20:00～) */
    LATE_WEEKEND(today -> today.isLateTime() && today.isWeekEnd());
    // ...略
    public static TermRule matches(Today today){
        Optional<TermRule> optional =  Stream.of(TermRule.values())
            .filter(t -> t.test(today))
            .findFirst();
            return Optional.ofNullable(optional.get()).orElseThrow(IllegalArgumentException::new);
    }
    // ...略
```


`Pricing`はチケット代の計算を行うクラスです。購入できるチケット候補と上映タームから、最も安いチケットを検索します。

``` java
   /* チケット代の計算クラス */
  public class Pricing {
      public static Price calculate(Attempt attempt) {
          //購入できるチケット候補の取得
          val candidateTickets = TicketRule.matches(attempt.getCustomer());
          //上映ターム(平日/祝日・レイトショーか否か)の取得
          val termRule = TermRule.matches(attempt.getToday());
          //チケット候補・上映タームから最も安いチケットを検索
          val priceOptional = candidateTickets.stream()
              .map(ticket -> ticket.getPriceByTerm(termRule) )
              .min(Comparator.comparingInt(Price::getValue));
          return priceOptional.orElseThrow(IllegalArgumentException::new);
      }
  }
```
