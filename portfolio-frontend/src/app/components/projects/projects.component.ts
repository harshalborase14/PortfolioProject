import { Component } from '@angular/core';

@Component({
  selector: 'app-projects',
  templateUrl: './projects.component.html',
})
export class ProjectsComponent {
  projects = [
    {
      title: 'Hotel Management System',
      description: 'Web app using JSP, Servlet & Hibernate.',
      link: '#'
    },
    {
      title: 'Voice Assistant (Swara)',
      description: 'Jarvis-like AI voice assistant built in JavaScript.',
      link: '#'
    },
    {
      title: 'Book Store',
      description: 'CRUD Java App using MySQL.',
      link: '#'
    }
  ];
}