package lotto;

import lotto.controller.LottoController;
import lotto.dto.LottoResultDto;
import lotto.dto.LottoTicketsDto;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {

    public static void main(String[] args) {
        LottoService lottoService = new LottoService();
        LottoController lottoController = new LottoController(lottoService);

        LottoTicketsDto lottoTicketsDto = lottoController.getLottoTickets(InputView.requestMoney(),
                InputView.requestManualLotto());
        OutputView.displayLottoTickets(lottoTicketsDto);

        LottoResultDto lottoResultDto = lottoController.getLottoResult(InputView.requestWinningNumbers(),
                InputView.requestBonusNumber());
        OutputView.displayLottoResult(lottoResultDto);
    }
}
