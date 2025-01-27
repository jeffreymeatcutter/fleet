import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Personnel } from '../models/personnel/personnel';
import { PersonnelDTO } from '../models/personnel/personnelDTO';

@Injectable({
  providedIn: 'root',
})
export class PersonnelService {
  private apiUrl = 'http://localhost:8667/personnel';
  constructor(private http: HttpClient) {}

  findAll(): Observable<Personnel[]> {
    return this.http.get<Personnel[]>(`${this.apiUrl}`);
  }

  findOne(id: number): Observable<Personnel> {
    return this.http.get<Personnel>(`${this.apiUrl}/${id}`);
  }

  create(personnelToCreate: PersonnelDTO): Observable<Personnel> {
    return this.http.post<Personnel>(`${this.apiUrl}`, personnelToCreate);
  }

  update(id: number, personnelToUpdate: PersonnelDTO): Observable<Personnel> {
    return this.http.put<Personnel>(`${this.apiUrl}/${id}`, personnelToUpdate);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
  findPersonnelBySquadronId(id: number): Observable<Personnel[]> {
      return this.http.get<Personnel[]>(`${this.apiUrl}/squadron/${id}`)
    }
}
