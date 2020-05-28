import React from 'react';
import './App.css';
import ViewListIcon from '@material-ui/icons/ViewList';
import AppLayout from "./components/AppLayout";
import ExampleItems from "./components/ExampleItems/ExampleItems";
import {APP_CONTENT_ROOT} from "./rest";

const applicationHeader = "Some Project"

const menuItemsAndContent = [
  {
    key: 1,
    path: `${APP_CONTENT_ROOT}/examples`,
    text: 'Example items',
    icon: <ViewListIcon />,
    component: ExampleItems
  }
];

function App() {
  return <AppLayout menuItemsAndContent = {menuItemsAndContent} appHeader = {applicationHeader}/>;
}

export default App;
