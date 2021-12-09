import * as AuthActions from "../Actions/auth.actions";

const initialState = {
  accountDetails: null,
  authenticated: false,
  validating: false,
  error: null,
  cred: {
    username: "j2ee",
    password: "j2ee",
  },
  signingUp: false,
  signUpResult: null,
  errorInSignup: null,
};

export default function (state = initialState, action) {
  switch (action.type) {
    case AuthActions.SIGNIN.REQUEST:
      return {
        ...state,
        validating: true,
        cred: action.payload,
      };
    case AuthActions.SIGNIN.SUCCESS:
      return {
        ...state,
        authenticated: true,
        validating: false,
      };
    case AuthActions.SIGNIN.FAILURE:
      return {
        ...state,
        authenticated: false,
        validating: false,
        error: action.payload,
      };
    case AuthActions.SIGNUP.REQUEST:
      return {
        ...state,
        signingUp: true,
        accountDetails: action.payload,
        cred: action.payload,
      };
    case AuthActions.SIGNUP.SUCCESS:
      return {
        ...state,
        signingUp: false,
        signUpResult: action.payload,
      };
    case AuthActions.SIGNUP.FAILURE:
      return {
        ...state,
        signingUp: false,
        errorInSignup: action.payload,
      };
    default:
      return state;
  }
}
