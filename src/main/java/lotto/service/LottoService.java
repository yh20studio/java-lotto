package lotto.service;

import java.util.List;
import lotto.domain.LottoStatistics;
import lotto.domain.LottoTickets;
import lotto.domain.Money;
import lotto.domain.WinningLotto;
import lotto.domain.lottonumbergenerator.LottoNumberAutoGenerator;
import lotto.domain.lottonumbergenerator.LottoNumberManualGenerator;
import lotto.dto.LottoResultDto;
import lotto.dto.LottoTicketsDto;

public class LottoService {

    public static final int LOTTO_PRICE = 1000;
    private static final String ERROR_ORDER_NUMBER = "금액이 부족하여 주문한 수만큼 로또를 구매할 수 없습니다.";

    private Money money;
    private List<List<Integer>> manualNumbers;
    private LottoTickets lottoTickets;

    public LottoTicketsDto publishLottoTickets(int money, List<List<Integer>> manualNumbers) {
        receiveOrder(money, manualNumbers);
        LottoTickets manualLottoTickets = publishManualLottoTickets();
        LottoTickets autoLottoTickets = publishAutoLottoTickets();
        lottoTickets = manualLottoTickets.combine(autoLottoTickets);
        return LottoTicketsDto.from(manualLottoTickets, autoLottoTickets);
    }

    public LottoResultDto matchLottoTickets(List<Integer> lottoNumbers, int bonusNumber) {
        WinningLotto winningLotto = new WinningLotto(lottoNumbers, bonusNumber);
        LottoStatistics lottoStatistics = new LottoStatistics(lottoTickets.getRanksBy(winningLotto));
        return LottoResultDto.from(lottoStatistics);
    }

    private void receiveOrder(int money, List<List<Integer>> manualNumbers) {
        this.money = new Money(money, LOTTO_PRICE);
        this.manualNumbers = manualNumbers;
        validateOrderNumber();
    }

    private void validateOrderNumber() {
        if (money.getMaximumPurchase(LOTTO_PRICE) < manualNumbers.size()) {
            throw new RuntimeException(ERROR_ORDER_NUMBER);
        }
    }

    private LottoTickets publishManualLottoTickets() {
        money = money.decrease(LOTTO_PRICE, manualNumbers.size());
        return new LottoTickets(new LottoNumberManualGenerator(manualNumbers), manualNumbers.size());
    }

    private LottoTickets publishAutoLottoTickets() {
        int autoLottoCount = money.getMaximumPurchase(LOTTO_PRICE);
        money = money.decrease(LOTTO_PRICE, manualNumbers.size());
        return new LottoTickets(new LottoNumberAutoGenerator(), autoLottoCount);
    }
}
