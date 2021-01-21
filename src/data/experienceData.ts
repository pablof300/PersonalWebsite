import LyftImage from '../assets/images/lyft.jpg'
import TwoSigmaImage from '../assets/images/two_sigma.png'
import CodeKidsImage from '../assets/images/codekids_logo.png'
import ItemImage from 'semantic-ui-react'

export interface PositionInfo {
    image: string
    company: string
    title: string
    date: string
    description: string
    labels: string[]
}
export const positions : PositionInfo[] = [
    {
        image: LyftImage,
        company: "Lyft",
        title: "Software Engineer Intern",
        date: "Summer 2020",
        description: "Virtually interned at Lyft Level 5, Lyft's self driving division in Palo Alto. I improved Level 5's web tools, used for autonomous ride playback and analysis, by migrating its web analytics to store data in BigQuery using gRPC. My project increased web analytics by 10% and refactored eight dashboards to provide better visualization of tool usage.",
        labels: ['gRPC', 'BigQuery', 'TypeScript', 'Autonomous Vehicles']
    },
    {
        image: TwoSigmaImage,
        company: "Two Sigma",
        title: "Software Engineer Intern",
        date: "Summer 2019",
        description: "Worked as a software engineering intern at the operations team of Two Sigma, a quantitative hedge fund, in Houston. I developed a visualization tool for the Kafka network to provide better metrics, health checks and alerts, and wider understanding of the all the interactions between services. I was able to experience the domain of finance throughout that summer.",
        labels: ['Kafka', 'Dropwizard', 'React', 'Java']
    },
    {
        image: CodeKidsImage,
        company: "CodeKids",
        title: "Intern",
        date: "Summer 2016",
        description: "Interned at a local summer technology camp, centered around engineering and programming for kids. Throughout three months, I had the opportunity to teach over 30 campers (ages 8-12 years) a curriculum of introductory programming, robot design, electronics, and engineering principles.",
        labels: ['Engineering', 'Creativity', 'Teaching']
    },
]