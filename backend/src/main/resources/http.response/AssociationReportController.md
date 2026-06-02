# 📦 Association Reports API

### Prerequisites
### - Ensure the following exist before testing:

- At least 1 Association
- At least 1 Vendor with bills (some PAID, some OVERDUE)
- At least 1 Unit with invoices created via POST /api/v1/units/{unitId}/invoices
- At least 1 Owner assigned to a unit

### Report 1 — Vendor Spending
### -Test 1a — All associations, last year (default)

## 🔄 Endpoint: GET Vendor Spending
### ✅ Request Details

- **Type**: GET
- **URL**: ` {{baseUrl}}/api/v1/reports/vendor-spending?dateRange=LAST_YEAR`
- **Request Name**: Get Vendor Spending


### ✅ Response Body (JSON) — Success
```json
{
  "success": true,
  "data": {
    "from": "2025-06-01",
    "to": "2026-06-01",
    "totalSpent": 1400.00,
    "vendors": [
      {
        "vendorId": 1,
        "vendorName": "John Smith",
        "serviceCategory": "Landscaping",
        "billCount": 2,
        "totalBilled": 1400.00,
        "totalPaid": 1400.00,
        "outstanding": 0.00
      },
      {
        "vendorId": 10,
        "vendorName": "Unknown",
        "serviceCategory": "—",
        "billCount": 1,
        "totalBilled": 800.00,
        "totalPaid": 0,
        "outstanding": 800.00
      }
    ]
  }
}
```
- **Response Status**: 200 OK
- ----

### -Test 1b  — Specific association

### ✅ Request Details

- **Type**: GET
- **URL**: `/api/v1/reports/vendor-spending?associationId=1&dateRange=LAST_YEAR`
- **Request Name**: Get Vendor Spending


### ✅ Response Body (JSON) — Success
```json
{
  "success": true,
  "data": {
    "from": "2025-06-01",
    "to": "2026-06-01",
    "totalSpent": 1400.00,
    "vendors": [
      {
        "vendorId": 1,
        "vendorName": "John Smith",
        "serviceCategory": "Landscaping",
        "billCount": 2,
        "totalBilled": 1400.00,
        "totalPaid": 1400.00,
        "outstanding": 0.00
      },
      {
        "vendorId": 10,
        "vendorName": "Unknown",
        "serviceCategory": "—",
        "billCount": 1,
        "totalBilled": 800.00,
        "totalPaid": 0,
        "outstanding": 800.00
      }
    ]
  }
}
```
- **Response Status**: 200 OK
- ----
### -Test 1c —  Custom date range
### ✅ Request Details

- **Type**: GET
- **URL**: `/api/v1/reports/vendor-spending?dateRange=CUSTOM&from=2026-01-01&to=2026-03-31`
- **Request Name**: Get Vendor Spending


### ✅ Response Body (JSON) — Success
```json
{
  "success": true,
  "data": {
    "from": "2026-01-01",
    "to": "2026-03-31",
    "totalSpent": 0,
    "vendors": []
  }
}
```
- **Response Status**: 200 OK
- ----
## Report 2 — Assessment History 
### -Test 2a — All associations, last year
## 🔄 Endpoint: GET Assesment History
### ✅ Request Details

- **Type**: GET
- **URL**: `/api/v1/reports/assessment-history?dateRange=LAST_YEAR`
- **Request Name**: Get Assesment History


### ✅ Response Body (JSON) — Success
```json
{
  "success": true,
  "data": {
    "from": "2025-06-01",
    "to": "2026-06-01",
    "totalAssessed": 0,
    "totalCollected": 0,
    "collectionRate": 0,
    "assessments": []
  }
}
```
- **Response Status**: 200 OK
- ----
### -Test 2b —Specific association filter

### ✅ Request Details

- **Type**: GET
- **URL**: `{{baseUrl}}/api/v1/reports/assessment-history?associationId=1&dateRange=THIS_YEAR`
- **Request Name**: Get Assesment History


### ✅ Response Body (JSON) — Success
```json
{
  "success": true,
  "data": {
    "from": "2026-01-01",
    "to": "2026-06-01",
    "totalAssessed": 2500.00,
    "totalCollected": 2500.00,
    "collectionRate": 100.0000,
    "assessments": [
      {
        "invoiceId": 1,
        "associationName": "Green Valley Residency",
        "unitNumber": "A-101",
        "ownerName": "John Doe",
        "invoiceDate": "2026-04-20",
        "dueDate": "2026-05-05",
        "amount": 2500.00,
        "status": "PAID"
      }
    ]
  }
}
```
- **Response Status**: 200 OK
- ----

### -Test 2c — Custom date range

### ✅ Request Details

- **Type**: GET
- **URL**: `{{baseUrl}}/api/v1/reports/assessment-history?dateRange=CUSTOM&from=2026-04-01&to=2026-04-30`
- **Request Name**: Get Assesment History


### ✅ Response Body (JSON) — Success
```json
{
  "success": true,
  "data": {
    "from": "2026-04-01",
    "to": "2026-04-30",
    "totalAssessed": 0,
    "totalCollected": 0,
    "collectionRate": 0,
    "assessments": []
  }
}
```
- **Response Status**: 200 OK
- ----

## Report 3 —  Unit Owner Statement
### -Test 3a —full statement
## 🔄 Endpoint: GET Unit owner statement
### ✅ Request Details

- **Type**: GET
- **URL**: `{{baseUrl}}/api/v1/reports/unit-owner-statement?associationId=1&unitId=1&from=2026-01-01&to=2026-05-31`
- **Request Name**: Get Unit owner statement


### ✅ Response Body (JSON) — Success
```json
{
  "success": true,
  "data": {
    "unitId": 1,
    "unitNumber": "A-101",
    "associationName": "Green Valley Residency",
    "ownerName": "John Doe",
    "ownerEmail": "john.doe@example.com",
    "from": "2026-01-01",
    "to": "2026-05-31",
    "openingBalance": -2150.00,
    "totalCharges": 2500.00,
    "totalPayments": 1750.0000,
    "closingBalance": -1400.0000,
    "transactions": [
      {
        "date": "2026-04-17",
        "description": "Cash payment: BILL-002",
        "type": "PAYMENT",
        "amount": 700.0000,
        "runningBalance": -2850.0000
      },
      {
        "date": "2026-04-20",
        "description": "Invoice #1",
        "type": "CHARGE",
        "amount": 2500.00,
        "runningBalance": -350.0000
      },
      {
        "date": "2026-05-01",
        "description": "Monthly HOA Fee",
        "type": "PAYMENT",
        "amount": 300.0000,
        "runningBalance": -650.0000
      },
      {
        "date": "2026-05-01",
        "description": "Pool Maintenance Assessment",
        "type": "PAYMENT",
        "amount": 50.0000,
        "runningBalance": -700.0000
      },
      {
        "date": "2026-05-04",
        "description": "Bill payment - Cash (Operating Checking — Updated): BILL-003",
        "type": "PAYMENT",
        "amount": 700.0000,
        "runningBalance": -1400.0000
      }
    ]
  }
}
```
- **Response Status**: 200 OK
- ----
### -Test 3b — Missing associationId (must return 400)

### ✅ Request Details

- **Type**: GET
- **URL**: `{{baseUrl}}/api/v1/reports/unit-owner-statement?unitId=1&from=2026-01-01&to=2026-05-31`
- **Request Name**: Get unit owner statement


### ✅ Response Body (JSON) — Success
```json
{
  "success": false,
  "error": "Missing parameter: associationId",
  "errorCode": "BAD_REQUEST"
}
```
- **Response Status**: 400 Bad Request
- ----

### -Test 3c —  Missing unitId (must return 400)

### ✅ Request Details

- **Type**: GET
- **URL**: `{{baseUrl}}/api/v1/reports/unit-owner-statement?unitId=1&from=2026-01-01&to=2026-05-31`
- **Request Name**: Get unit owner statement


### ✅ Response Body (JSON) — Success
```json
{
  "success": false,
  "error": "Missing parameter: associationId",
  "errorCode": "BAD_REQUEST"
}
```
- **Response Status**: 400 Bad Request
- ----

### -Test 3d — Missing from/to dates (must return 400)

### ✅ Request Details

- **Type**: GET
- **URL**: `{{baseUrl}}/api/v1/reports/unit-owner-statement?associationId=1&unitId=99999&from=2026-01-01&to=2026-05-31`
- **Request Name**: Get unit owner statement


### ✅ Response Body (JSON) — Success
```json
{
  "success": false,
  "error": "Missing parameter: associationId",
  "errorCode": "BAD_REQUEST"
}
```
- **Response Status**: 400 Bad Request
- ----