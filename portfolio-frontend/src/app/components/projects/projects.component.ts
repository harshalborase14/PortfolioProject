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
      link: 'https://github.com/harshalborase14/Hotel-Management-System'
    },
    {
      title: 'Voice Assistant (Swara)',
      description: 'Jarvis-like AI voice assistant built in JavaScript.',
      link: 'https://github.com/harshalborase14/S.W.A.R.A..git'
    },
    {
      title: 'Book Store',
      description: 'CRUD Java App using MySQL.',
      link: 'https://github.com/harshalborase14/Simple_Book-Store_Project.git'
    }
  ];
}
