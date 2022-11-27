import {readFileSync} from 'fs'

export function getInput(file: string) {
    return readFileSync(file, {encoding: 'utf-8', flag: 'r'})
}