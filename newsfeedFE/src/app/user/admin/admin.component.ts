import {Component, OnInit} from '@angular/core';
import {TranslateService} from "@ngx-translate/core";
import {AdminService} from "../admin.service";
import {MatDialog} from "@angular/material";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit {
  userData;
  button: string;
  dataSource: any;
  displayedColumns: string[] = [
    'username',
    'activation'
  ];

  constructor(private translate: TranslateService,
              private usrMgmtService: AdminService,
              public dialog: MatDialog) {
  }

  getActivationButtonText(isActive) {
    if (isActive) {

      return "Deactivate";

    } else {

      return "Activate"

    }
  }

  // openDeactivationErrorDialog(user): void {
  //   const dialogRef = this.dialog.open(DeactivationPopupComponent, {
  //     width: '300px',
  //     data: {
  //       email: user.email
  //     }
  //   });
  // }

  activateUser(email) {
    this.usrMgmtService.activateUser(email)
      .subscribe(
        data => {
          this.getUsers();
        }
      );
  }

  deactivateUser(email) {
    this.usrMgmtService.deactivateUser(email)
      .subscribe(
        data => {
          this.getUsers();
        },
        error1 => {
          if (error1.status == 302) {
            //this.openDeactivationErrorDialog(email);
            console.log("Deactivate failed");
          }
        }
      );
  }

  toggleActivation(isActive, email) {
    if (isActive) {
      this.deactivateUser(email);
    } else {
      this.activateUser(email);
    }
  }

  getUsers() {
    this.usrMgmtService.getAllUsers()
      .subscribe(
        data => {
          this.dataSource = data;
        }
      )
  }

  ngOnInit() {
    this.getUsers();
    this.userData = {
      email: "",
      password: ""
    }
  }

}
