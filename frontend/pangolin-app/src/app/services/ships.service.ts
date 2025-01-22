import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Ships } from '../models/ships/ships';
import { Observable } from 'rxjs';
import { ShipsDTO } from '../models/ships/shipsDTO';

@Injectable({
  providedIn: 'root',
})
export class ShipsService {
  private apiUrl = 'http://localhost:8667/ships';
  constructor(private http: HttpClient) {}

  findAll(): Observable<Ships[]> {
    return this.http.get<Ships[]>(`${this.apiUrl}`);
  }

  findOne(id: number): Observable<Ships> {
    return this.http.get<Ships>(`${this.apiUrl}/${id}`);
  }

  create(shipsToCreate: ShipsDTO): Observable<Ships> {
    return this.http.post<Ships>(`${this.apiUrl}`, shipsToCreate);
  }

  update(id: number, shipsToUpdate: ShipsDTO): Observable<Ships> {
    return this.http.put<Ships>(`${this.apiUrl}/${id}`, shipsToUpdate);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}
