import {Injectable} from '@angular/core';

export const TOKEN_KEY = 'TOKEN';

@Injectable({
  providedIn: 'root'
})
export class StoreService {

  public saveToken(token: string) {
    localStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string | null {
    return localStorage.getItem(TOKEN_KEY);
  }

  public deleteToken() {
    localStorage.removeItem(TOKEN_KEY);
  }

}
