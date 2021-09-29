export interface LoginResponse {
  token: string;
}

export interface TokenCheckResponse {
  userId: string;
  roles: string[];
  token: string;
}
