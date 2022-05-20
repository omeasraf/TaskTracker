import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Task } from "../Models/Task";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { environment } from "src/environments/environment";

@Injectable({
  providedIn: "root",
})
export class TaskService {
  private apiURL = `${environment.apiURL}/tasks`;
  constructor(private httpClient: HttpClient) {}

  getTasks(): Observable<Task[]> {
    return this.httpClient.get<Task[]>(this.apiURL + "/all");
  }

  deleteTask(id?: number): Observable<void> {
    return this.httpClient.delete<void>(this.apiURL + `/delete/${id}`);
  }

  toggleTask(task: Task): Observable<Task> {
    task.reminder = !task.reminder;

    return this.httpClient.put<Task>(this.apiURL + "/update", task);
  }

  addTask(task: Task): Observable<Task> {
    task.reminder = !task.reminder;

    return this.httpClient.post<Task>(this.apiURL + "/add", task);
  }
}
