import { CommonModule, NgIf } from '@angular/common';
import { Component, Input } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { Ships, ShipType } from '../../models/ships/ships';
import { SquadronService } from '../../services/squadron.service';

@Component({
  selector: 'app-ships',
  imports: [MatCardModule, NgIf, CommonModule],
  templateUrl: './ships.component.html',
  styleUrl: './ships.component.css',
  standalone: true
})
export class ShipsComponent {
  constructor(private squadronService: SquadronService) {}
    @Input() ship: Ships | null = null;
    squadName: string = "";
    shipTypes = Object.values(ShipType);

    ngOnInit(): void {
      if(this.ship){
        this.squadronService.findOne(this.ship.squadronId).subscribe((data) => {
          this.squadName = data.squadronName;
        })
      }
    }
}
