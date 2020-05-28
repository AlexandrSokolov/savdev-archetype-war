import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import TableRow from "@material-ui/core/TableRow";
import TableCell from "@material-ui/core/TableCell";

const useRowStyles = makeStyles({
  root: {
    '& > *': {
      borderBottom: 'unset',
    },
  },
});

const ExampleItem = (props) => {

  const { id, name } = props;
  const classes = useRowStyles();

  return (
    <React.Fragment>
      <TableRow key={id} className={classes.root}>
        <TableCell component="th" scope="row">
          {name}
        </TableCell>
      </TableRow>
    </React.Fragment>
  )
}

export default ExampleItem

