import { Injectable } from '@angular/core';
import { Observable } from "rxjs";
import { Project } from "./model/project";
import { HttpClient } from "@angular/common/http";
import { environment } from "../../environments/environment";

const BASE_URL = `${environment.serverUrl}/api/projects`;

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  constructor(private httpClient: HttpClient) {
  }

  getProjects(): Observable<Project[]> {
    return this.httpClient.get<Project[]>(BASE_URL);
  }
}
