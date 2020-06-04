import React, {Component} from "react";
import ExampleItem from "./ExampleItem";
//import RestService, {APP_CONTENT_ROOT} from "../../rest";
import RestService from "../../rest";
import TableContainer from "@material-ui/core/TableContainer";
import Paper from "@material-ui/core/Paper";
import TableCell from "@material-ui/core/TableCell";
import TableRow from "@material-ui/core/TableRow";
import TableHead from "@material-ui/core/TableHead";
import TableBody from "@material-ui/core/TableBody";
import Table from "@material-ui/core/Table";
import withStyles from "@material-ui/core/styles/withStyles";

const useStyles = theme => ({
  table: {
    minWidth: 650,
  },
});

class ExampleItems extends Component {

  state = {
    exampleItems: []
  };

  constructor(props) {
    super(props)
    //this.restService = new RestService((err) => this.props.history.push(`${APP_CONTENT_ROOT}/historyLog/error/${err}`));
    this.restService = new RestService((err) => console.log(err));
    this.onDelete = this.onDelete.bind(this);
  }

  componentDidMount() {
    this.restService.getData(
      "/examples",
      exampleItems => this.setState({ exampleItems: exampleItems }))
  }

  onDelete(item) {
    this.restService.delete(
      `/examples/${item.id}`,
      () => this.setState({
        exampleItems: this.state.exampleItems.filter(st => st.id !== item.id)}
      ));
  }

  render() {
    const { classes } = this.props;
    return (
      <TableContainer component={Paper}>
        <Table className={classes.table} aria-label="simple table">
          <TableHead>
            <TableRow>
              <TableCell>Name</TableCell>
              <TableCell>Status</TableCell>
              <TableCell>Action</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {this.state.exampleItems
              .sort((a, b) => Date.parse(b.created) - Date.parse(a.created))
              .map(item => (
              <ExampleItem {...item} onDelete={ () => this.onDelete(item)} />
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    )
  }
}

export default withStyles(useStyles)(ExampleItems)
