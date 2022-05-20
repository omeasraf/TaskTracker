import { Component, OnInit, Output, EventEmitter } from "@angular/core";
import { Subscription } from "rxjs";
import { Task } from "src/app/Models/Task";
import { UiService } from "src/app/services/ui.service";

@Component({
  selector: "app-add-task",
  templateUrl: "./add-task.component.html",
  styleUrls: ["./add-task.component.css"],
})
export class AddTaskComponent implements OnInit {
  @Output() onAddTask: EventEmitter<Task> = new EventEmitter();
  title?: String;
  day?: String;
  reminder: boolean = false;
  showAddTask: boolean = false;
  subscription?: Subscription;

  constructor(private uiService: UiService) {
    this.subscription = this.uiService
      .onToggle()
      .subscribe((value) => (this.showAddTask = value));
  }

  ngOnInit(): void {}
  onSubmit(): void {
    if (!this.title) {
      alert("Please add task title!");
    }

    const newTask: Task = {
      title: this.title!,
      day: this.day!,
      reminder: this.reminder!,
    };

    this.onAddTask.emit(newTask);

    this.title = "";
    this.day = "";
    this.reminder = false;
  }
}
