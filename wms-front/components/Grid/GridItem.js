import React from "react";
// nodejs library to set properties for components
import PropTypes from "prop-types";
// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";
import Grid from "@material-ui/core/Grid";

const styles = {
  grid: {
    position: "relative",
    width: "100%",
    minHeight: "1px",
    paddingRight: "15px",
    paddingLeft: "15px",
    flexBasis: "auto",
  },
};

const useStyles = makeStyles(styles);

export default function GridItem({ children, className = "", ...rest }) {
  const classes = useStyles();
  
  return (
    <Grid item {...rest} className={`${classes.grid} ${className}`}>
      {children}
    </Grid>
  );
}

GridItem.propTypes = {
  children: PropTypes.node,
  className: PropTypes.string,
};
