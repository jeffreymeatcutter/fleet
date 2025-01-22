import { Component } from '@angular/core';
import { Squadron } from '../../models/squadron/squadron';
import { SquadronService } from '../../services/squadron.service';
import { CommonModule } from '@angular/common';
import { SquadronComponent } from '../squadron/squadron.component';

@Component({
  standalone: true,
  selector: 'app-squadron-view',
  imports: [CommonModule, SquadronComponent],
  templateUrl: './squadron-view.component.html',
  styleUrl: './squadron-view.component.css',
})
export class SquadronViewComponent {
  squadrons: Squadron[] = [];

  constructor(private squadronService: SquadronService) {}

  ngOnInit() {
    //Populate squadrons on init
    this.squadronService.findAll().subscribe((data) => {
      this.squadrons = data;
    });
  }
  // Function to track items by their unique ID
  trackBySquadronId(index: number, squadron: Squadron): number | undefined {
    return squadron.squadronId;
  }
}
