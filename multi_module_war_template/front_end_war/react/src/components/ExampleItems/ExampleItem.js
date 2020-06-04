import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import TableRow from "@material-ui/core/TableRow";
import TableCell from "@material-ui/core/TableCell";
import DeleteIcon from '@material-ui/icons/Delete';
import IconButton from "@material-ui/core/IconButton";

const useRowStyles = makeStyles({
  root: {
    '& > *': {
      borderBottom: 'unset',
    },
  },
});

const ExampleItem = (props) => {

  const { id, name, status, onDelete } = props;
  const classes = useRowStyles();

  return (
    <React.Fragment>
      <TableRow key={id} className={classes.root}>
        <TableCell component="th" scope="row">
          {name}
        </TableCell>
        <TableCell>{status}</TableCell>
        <TableCell align="right">
          <IconButton onClick={onDelete}>
            <DeleteIcon fontSize="small" />
          </IconButton>
        </TableCell>
      </TableRow>
    </React.Fragment>
  )
}

export default ExampleItem

