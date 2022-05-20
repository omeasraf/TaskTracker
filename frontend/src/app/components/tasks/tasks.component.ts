import { Component, OnInit } from "@angular/core";
import { TaskService } from "src/app/services/task.service";
import { Task } from "../../Models/Task";

@Component({
  selector: "app-tasks",
  templateUrl: "./tasks.component.html",
  styleUrls: ["./tasks.component.css"],
})
export class TasksComponent implements OnInit {
  tasks: Task[] = [];
  constructor(private taskService: TaskService) {}

  ngOnInit(): void {
    this.taskService.getTasks().subscribe((tasks) => (this.tasks = tasks));
  }

  deleteTask(task: Task): void {
    this.taskService
      .deleteTask(task?.id)
      .subscribe(
        () => (this.tasks = this.tasks.filter((t) => t.id !== task.id))
      );
  }

  toggleTask(task: Task): void {
    this.taskService.toggleTask(task).subscribe((t) => {
      this.tasks.map((tsk) => {
        if (tsk.id == t.id) {
          tsk = t;
        }
      });
    });
  }

  addTask(task: Task): void {
    this.taskService.addTask(task).subscribe((t) => {
      this.tasks.push(t);
    });
  }
}
