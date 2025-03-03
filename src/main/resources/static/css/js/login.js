import jwtDecode from 'jwt-decode';

// Retrieve token from storage (localStorage, sessionStorage, or cookies)
const token = localStorage.getItem('token');

// Decode the token to extract roles
const decodedToken = jwtDecode(token);
const roles = decodedToken.roles || []; // Ensure roles exist
