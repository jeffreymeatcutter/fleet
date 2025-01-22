import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Squadron } from '../models/squadron/squadron';
import { SquadronDTO } from '../models/squadron/squadronDTO';

@Injectable({
  providedIn: 'root',
})
export class SquadronService {
  private apiUrl = 'http://localhost:8667/squadron';
  constructor(private http: HttpClient) {}

  findAll(): Observable<Squadron[]> {
    return this.http.get<Squadron[]>(`${this.apiUrl}`);
  }

  findOne(id: number): Observable<Squadron> {
    return this.http.get<Squadron>(`${this.apiUrl}/${id}`);
  }

  create(squadronToCreate: SquadronDTO): Observable<Squadron> {
    return this.http.post<Squadron>(`${this.apiUrl}`, squadronToCreate);
  }

  update(id: number, squadronToUpdate: SquadronDTO): Observable<Squadron> {
    return this.http.put<Squadron>(`${this.apiUrl}/${id}`, squadronToUpdate);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}
