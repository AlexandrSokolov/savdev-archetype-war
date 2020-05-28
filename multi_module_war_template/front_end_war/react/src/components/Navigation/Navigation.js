import React from 'react';
import Drawer from '@material-ui/core/Drawer';
import List from '@material-ui/core/List';
import NavigationItem from './NavigationItem'
import Divider from "@material-ui/core/Divider";

const navigation = (props) => {

  const { classes, items } = props;

  return (
    <nav>
      <Drawer
        className={classes.drawer}
        classes={{
          paper: classes.drawerPaper,
        }}
        variant="permanent"
        anchor="left">
        <div className={classes.toolbar} />
        <Divider />
        <List>
          {items.map( item => (
            <NavigationItem {...item} />
          ))}
        </List>
      </Drawer>
    </nav>
  );
};

export default navigation