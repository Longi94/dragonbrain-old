import { Component, HostListener, OnInit } from '@angular/core';
import { ProjectService } from "../../shared/project.service";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit {

  scrollY: number = 0;

  constructor(private projectService: ProjectService) {
  }

  ngOnInit() {
    this.projectService.getProjects().subscribe(projects => {
      console.log(projects);
    }, console.error);
  }

  @HostListener('window:scroll', ['$event'])
  onWindowScroll($event: Event) {
    this.scrollY = window.scrollY;
  }
}
