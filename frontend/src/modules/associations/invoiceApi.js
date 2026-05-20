import httpClient from "../../api/httpClient";

// ─── Chart of Accounts ────────────────────────────────────────────────────────
export const getCoaAccounts = () =>
  httpClient.get("/api/v1/accounting/coa");

// ─── Create Invoice ───────────────────────────────────────────────────────────

export const createUnitInvoice = (unitId, data) =>
  httpClient.post(`/api/v1/units/${unitId}/invoices`, data);

// ─── List Invoices ────────────────────────────────────────────────────────────

export const getUnitInvoices = (unitId) =>
  httpClient.get(`/api/v1/units/${unitId}/invoices`);

// ─── Get Invoice By ID ────────────────────────────────────────────────────────

export const getUnitInvoiceById = (unitId, invoiceId) =>
  httpClient.get(`/api/v1/units/${unitId}/invoices/${invoiceId}`);