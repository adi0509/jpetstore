import { createRequestTypes, action } from "../../Utils/redux";

export const SIGNIN = createRequestTypes("SIGNIN");
export const SIGNUP = createRequestTypes("SIGNUP");

export const signIn = {
  request: (credentials) => action(SIGNIN.REQUEST, credentials),
  success: (data) => action(SIGNIN.SUCCESS, data),
  failure: (error) => action(SIGNIN.FAILURE, error),
};
export const signUp = {
  request: (accountDetails) => action(SIGNUP.REQUEST, accountDetails),
  success: (data) => action(SIGNUP.SUCCESS, data),
  failure: (error) => action(SIGNUP.FAILURE, error),
};
