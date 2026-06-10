import { Route } from "react-router-dom";
import BalanceSheetReportPage    from "./pages/BalanceSheetReportPage";
import IncomeStatementReportPage from "./pages/IncomeStatementReportPage";
import TrialBalanceReportPage    from "./pages/TrialBalanceReportPage";
import CashFlowReportPage        from "./pages/CashFlowReportPage";
import VendorLedgerReportPage    from "./pages/VendorLedgerReportPage";
import BudgetVsActualReportPage  from "./pages/BudgetVsActualReportPage";

export const financialReportRoutes = (
  <>
    <Route path="reports/balance-sheet"     element={<BalanceSheetReportPage    />} />
    <Route path="reports/income-statement"  element={<IncomeStatementReportPage />} />
    <Route path="reports/trial-balance"     element={<TrialBalanceReportPage    />} />
    <Route path="reports/cash-flow"         element={<CashFlowReportPage        />} />
    <Route path="reports/vendor-ledger"     element={<VendorLedgerReportPage    />} />
    <Route path="reports/budget-vs-actual"  element={<BudgetVsActualReportPage  />} />
  </>
);