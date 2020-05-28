import React from 'react';
import { ListItem, ListItemIcon, ListItemText } from '@material-ui/core';
import { Link } from "react-router-dom";

const navigationItem = ({text, icon, path }) => {

  return (
    <ListItem button component={Link} to={path}>
      <ListItemIcon>{icon}</ListItemIcon>
      <ListItemText primary={text}/>
    </ListItem>
  )
};

export default navigationItem

