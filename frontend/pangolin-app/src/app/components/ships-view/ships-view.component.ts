import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ShipsComponent } from '../ships/ships.component';
import { Ships } from '../../models/ships/ships';
import { ShipsService } from '../../services/ships.service';

@Component({
  selector: 'app-ships-view',
  imports: [CommonModule, ShipsComponent],
  templateUrl: './ships-view.component.html',
  styleUrl: './ships-view.component.css',
  standalone: true
})
export class ShipsViewComponent {
  ships: Ships[] = [];

  constructor(private shipsService: ShipsService) {}

  ngOnInit() {
        //Populate ships on init
        this.shipsService.findAll().subscribe((data) => {
          this.ships = data;
        });
      }
      // Function to track items by their unique ID
      trackByShipId(index: number, ship: Ships): number | undefined {
        return ship.shipId;
      }
}
