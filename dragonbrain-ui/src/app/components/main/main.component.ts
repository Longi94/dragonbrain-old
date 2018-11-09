import { Component, OnInit } from '@angular/core';
import { ProjectService } from "../../shared/project.service";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit {

  constructor(private projectService: ProjectService) {
  }

  ngOnInit() {
    this.projectService.getProjects().subscribe(projects => {
      console.log(projects);
    }, console.error);
  }

}
