export const authConfig = {
    clientId: 'oauth2-pkce-demo',
    authorizationEndpoint: 'http://localhost:8443/realms/master/protocol/openid-connect/auth',
    tokenEndpoint: 'http://localhost:8443/realms/master/protocol/openid-connect/token',
    redirectUri: 'http://localhost:5173/callback', // Add a specific callback path
    redirectUri: 'http://localhost:5173', // Or use root if your app handles it there
    scope: 'openid profile email',
    responseType: 'code', // Make sure this is set for Authorization Code flow
    onRefreshTokenExpire: (event) => event.logIn(),
};