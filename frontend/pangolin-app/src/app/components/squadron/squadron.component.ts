import { Component, Input } from '@angular/core';
import { Squadron } from '../../models/squadron/squadron';
import { MatCardModule } from '@angular/material/card'
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-squadron',
  imports: [MatCardModule, NgIf],
  templateUrl: './squadron.component.html',
  styleUrl: './squadron.component.css',
  standalone: true
})
export class SquadronComponent {
  @Input() squadron: Squadron| null = null;
}
