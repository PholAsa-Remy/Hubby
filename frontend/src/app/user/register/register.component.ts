import { Component } from '@angular/core';
import {UserService} from "../user.service";
import {FormControl, FormGroup, ReactiveFormsModule} from "@angular/forms";

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css',
  providers:[UserService]
})
export class RegisterComponent {
  constructor(private userService: UserService) {}

  registerForm = new FormGroup(
    {
      username : new FormControl (''),
      password : new FormControl(''),
    }
  )
  onSubmit () {
    console.log(this.registerForm.value.username! + this.registerForm.value.password!);
    this.userService.register(this.registerForm.value.username!, this.registerForm.value.password!)
    this.registerForm.setValue({
      username: '',
      password: ''
    })
  }
}
