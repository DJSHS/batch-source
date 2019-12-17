import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NavComponent } from './components/nav/nav.component';
import { HighlightComponent } from './components/highlight/highlight.component';
import { ProfileComponent } from './components/profile/profile.component';
import { SelectComponent } from './components/select/select.component';
import { TableComponent } from './components/table/table.component';
import { UserComponent } from './components/user/user.component';

const routes: Routes = [
  { path: "", redirectTo: "", pathMatch: "full" },
  { path: "nav", component: NavComponent},
  { path: "highlight", component: HighlightComponent},
  { path: "profile", component: ProfileComponent},
  { path: "select", component: SelectComponent},
  { path: "table", component: TableComponent},
  { path: "user", component: UserComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
