import React from "react"
import "./DownIcon.css"
import { Grid, Icon } from "semantic-ui-react"

const DownIcon = () => {
    return(
        <Grid centered className={"IconContainer"}>
          <Icon className={"Icon"} size="big" name="angle down" />
        </Grid>
    )
}

export default DownIcon