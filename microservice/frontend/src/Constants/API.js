export const BASE_URL = "http:/";
// export const ACCOUNT_BASE_URL = "/api-account:8080";
// export const CATALOGUE_BASE_URL = "/api-catalog:8080";
// export const ORDER_BASE_URL = "/api-order:8080";
const ACCOUNT_BASE_URL = "/52.167.202.59:8081";
const CATALOGUE_BASE_URL = "/52.167.202.59:8082";
const ORDER_BASE_URL = "/52.167.202.59:8083";
if (process.env.BASE_IP !== undefined) {
  ACCOUNT_BASE_URL = `/${process.env.REACT_APP_BASE_IP}:${process.env.REACT_APP_ACCOUNT_PORT}`;
  CATALOGUE_BASE_URL = `/${process.env.REACT_APP_BASE_IP}:${process.env.REACT_APP_CATALOGUE_PORT}`;
  ORDER_BASE_URL = `/${process.env.REACT_APP_BASE_IP}:${process.env.REACT_APP_ORDER_PORT}`;
}
export { ACCOUNT_BASE_URL, CATALOGUE_BASE_URL, ORDER_BASE_URL };
// export CATALOGUE_BASE_URL;
// export ORDER_BASE_URL;

/*
catalogue: 8082
account: 8081
order: 8083
*/
