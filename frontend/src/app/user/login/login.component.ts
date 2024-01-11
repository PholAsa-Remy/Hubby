import {Component, signal} from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {UserService} from "../user.service";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
  providers:[UserService]
})
export class LoginComponent {
  constructor(private userService: UserService) {}

  loginForm = new FormGroup(
    {
      username : new FormControl (''),
      password : new FormControl(''),
    }
  )
  onSubmit () {
    console.log(this.loginForm.value.username! + this.loginForm.value.password!);
    this.userService.login(this.loginForm.value.username!, this.loginForm.value.password!)
    this.loginForm.setValue({
      username: '',
      password: ''
    })
  }

}
