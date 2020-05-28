import CssBaseline from "@material-ui/core/CssBaseline/CssBaseline";
import AppBar from "@material-ui/core/AppBar/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import Typography from "@material-ui/core/Typography";
import {BrowserRouter as Router, Route, Redirect} from "react-router-dom";
import Navigation from "../Navigation/Navigation";
import React from "react";
import {makeStyles} from "@material-ui/core/styles";

const drawerWidth = 340;

const useStyles = makeStyles((theme) => ({
  root: {
    display: 'flex',
  },
  appBar: {
    width: `calc(100% - ${drawerWidth}px)`,
    marginLeft: drawerWidth,
  },
  drawer: {
    width: drawerWidth,
    flexShrink: 0,
  },
  drawerPaper: {
    width: drawerWidth,
  },
  // necessary for content to be below app bar
  toolbar: theme.mixins.toolbar,
  content: {
    flexGrow: 1,
    backgroundColor: theme.palette.background.default,
    padding: theme.spacing(3),
  },
}));

function AppLayout(props) {
  const classes = useStyles();

  const { menuItemsAndContent, appHeader } = props;

  return (
    <div className={classes.root}>
      <CssBaseline />
      <AppBar position="fixed" className={classes.appBar}>
        <Toolbar>
          <Typography variant="h6" noWrap>
            {appHeader}
          </Typography>
        </Toolbar>
      </AppBar>
      <Router>
        <Navigation classes={classes} items={menuItemsAndContent}/>
        <main
          className={classes.content}>
          <div className={classes.toolbar} />
            {menuItemsAndContent.map(item => <Route {...item} />)}
            {menuItemsAndContent
              .filter(item => item.key === 1)
              .map(item => <Redirect to={item.path} />)}
        </main>
      </Router>
    </div>
  );
}

export default AppLayout;