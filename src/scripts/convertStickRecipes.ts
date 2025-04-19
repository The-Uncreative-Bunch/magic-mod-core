/**
 * This script requires that you copy all of Minecraft's vanilla recipes into a
 * folder named "recipe" within the same directory.
 *
 * You will also need to have NodeJS installed along with Typescript and tsx
 * to run this script.
 *
 * Replace the values for the search and replace keys below this to search
 * for and replace all of the correct ingredients.
 */

/** The key to search for. This should almost always be a vanilla Minecraft item/tag id. */
const searchKey = 'minecraft:stick';
/** This is what replaces the search key. */
const replaceKey = '#magic_mod:sticks';


import * as fs from 'node:fs';
import * as path from "node:path";

const targetDirectory = path.join(__dirname, '/recipe');
console.log(`Target directory: ${targetDirectory}`);
fs.readdirSync(targetDirectory, { withFileTypes: false }).forEach(fileName => {
  console.log(`File path: ${fileName}`);
  fs.readFile(path.join(targetDirectory, fileName), 'utf8', (err, data) => {
    if (err) {
      console.error(err);
      return;
    }

    // Parse the json data.
    const recipe = JSON.parse(data);
    const ingredientKeys: { [key: string]: string } = recipe.key;
    if (ingredientKeys) {
      Object.keys(ingredientKeys).forEach(ingredientKey => {
        if (ingredientKeys[ingredientKey] === searchKey) {
          ingredientKeys[ingredientKey] = replaceKey;
          //output the new file with the same name in a "processed" folder.
          const newFilePath = __dirname + '/recipe/processed/' + fileName;
          console.log("writing file to path " + newFilePath);
          if (!fs.existsSync(path.dirname(newFilePath)))
            fs.mkdirSync(path.dirname(newFilePath));
          fs.writeFileSync(newFilePath, JSON.stringify(recipe), { flag: ''});
        }
      });
    }
  });
});