/* 2. contact.component.ts */
import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent {
  name: string = '';
  email: string = '';
  message: string = '';
  responseMessage: string = '';

  constructor(private http: HttpClient) {}

  submitForm(event: Event) {
    event.preventDefault();

    const formData = {
      name: this.name,
      email: this.email,
      message: this.message
    };

    console.log("\uD83D\uDD04 Sending data to backend:", formData);

    this.http.post('http://localhost:8080/PortfolioBackend/contactServlet', formData, { responseType: 'text' })
      .subscribe(
        (res) => {
          console.log("\u2705 Response:", res);
          this.responseMessage = res;
        },
        (err) => {
          console.error("\u274C Error from server:", err);
          this.responseMessage = "Something went wrong!";
        }
      );
  }
}